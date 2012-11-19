package org.uengine.visualmashup.service.listener;

import org.apache.ddlutils.Platform;
import org.apache.ddlutils.PlatformFactory;
import org.apache.ddlutils.io.DatabaseIO;
import org.apache.ddlutils.model.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.uengine.visualmashup.model.Entity;
import org.uengine.visualmashup.model.VmGadgets;
import org.uengine.visualmashup.service.VmGadgetsService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * This listener initializes or gracefully shuts down the database based on
 * events from the web application.
 */
public class DBLifecycleContextListener implements ServletContextListener {
    private DataSource dataSource = null;
    private WebApplicationContext springContext = null;
    private String dbName = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (dbName.equalsIgnoreCase("hsql")) {
            SimpleJdbcTemplate template = new SimpleJdbcTemplate(dataSource);
            template.update("SHUTDOWN;");
            logger.info("Database[{}] Successfully Shutdown.", new String[]{dbName});
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        dataSource = (DataSource) springContext.getBean("dataSource");
        dbName = springContext.getMessage("vm.database.name", null, null);

        // 1. DB Schema Update
        Platform platform = PlatformFactory.createNewPlatformInstance(dataSource);
        Database database = new DatabaseIO().read(new InputStreamReader(
                getClass().getResourceAsStream("/schema/ddl_" + dbName + ".xml")));
        platform.alterTables(database, false);
        logger.info("Database[{}] Successfully Updated.", new String[]{dbName});

        // 2. Gadget XML Export from DB(If Not Exists)
        VmGadgetsService vmGadgetsService = (VmGadgetsService) springContext.getBean("vmGadgetsService");
        List<? extends Entity> entityList = vmGadgetsService.list(null);
        File xmlFile = null;
        VmGadgets vmGadgets = null;
        for (Entity entity : entityList) {
            vmGadgets = (VmGadgets)entity;
            if (StringUtils.hasText(vmGadgets.getPath())) {
                xmlFile = new File(servletContext.getRealPath(vmGadgets.getPath()));
                if (!xmlFile.exists()) {
                    vmGadgets = (VmGadgets)vmGadgetsService.get(vmGadgets.getSeqNo());
                    try {
                        if (!xmlFile.getParentFile().exists()) xmlFile.getParentFile().mkdirs();
                        FileCopyUtils.copy(vmGadgets.getXmlContents().getBytes("UTF-8"), xmlFile);
                        logger.info("Gadget XML Exported. [{}]", xmlFile.getAbsolutePath());
                    } catch (IOException e) {
                        logger.warn("Gadget XML Export Failed!", e);
                    }
                }
            }
        }
    }
}