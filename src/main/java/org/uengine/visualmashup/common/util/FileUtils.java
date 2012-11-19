package org.uengine.visualmashup.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 파일 처리 유틸리티 클래스. 이 유틸리티 클래스는 파일을 복사하는 것과 같은 파일 처리의 기능을 제공하며 또한 입출력 스트림에 대한 복사 기능도 제공한다.
 *
 * @author Edward KIM
 * @author Ted Won
 * @since 1.0
 */
public class FileUtils {

    /**
     * SLF4J Logging
     */
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 파일을 복사한다. <p>
     * <pre>
     * File input = new File("input.txt");
     * File output = new File("output.txt");
     * FileUtils.copy(input, output); // 파일을 복사한다.
     * </pre>
     * </p>
     *
     * @param in  소스 파일
     * @param out 타겟 파일
     * @return 복사한 바이트 수
     * @throws java.io.IOException 입출력 에러 발생시
     */
    public static int copy(File in, File out) throws IOException {
        return org.springframework.util.FileCopyUtils.copy(in, out);
    }

    /**
     * 파일을 복사한다. <p>
     * <pre>
     * FileUtils.copy("input.txt", "output.txt"); // 파일을 복사한다.
     * </pre>
     * </p>
     *
     * @param in  소스 파일명
     * @param out 타겟 파일명
     * @return 복사한 바이트 수
     * @throws java.io.IOException 입출력 에러 발생시
     */
    public static int copy(String in, String out) throws IOException {
        return org.springframework.util.FileCopyUtils.copy(new File(in), new File(out));
    }

    /**
     * 바이트 배열을 파일로 저장한다.
     *
     * @param in  바이트 배열
     * @param out 파일
     * @throws java.io.IOException 입출력 에러 발생시
     */
    public static void copy(byte[] in, File out) throws IOException {
        org.springframework.util.FileCopyUtils.copy(in, out);
    }

    /**
     * 파일의 내용을 로딩하여 바이트 배열로 복사한다.
     *
     * @param in 복사할 파일
     * @return 파일의 바이트 배열
     * @throws java.io.IOException 입출력 에러 발생시
     */
    public static byte[] copyToByteArray(File in) throws IOException {
        return org.springframework.util.FileCopyUtils.copyToByteArray(in);
    }

    /**
     * 입력 스트림의 내용을 출력 스트림으로 복사한다.
     *
     * @param in  입력 스트림
     * @param out 출력 스트림
     * @return 복사한 바이트 수
     * @throws java.io.IOException 입출력 에러 발생시
     */
    public static int copy(InputStream in, OutputStream out) throws IOException {
        return org.springframework.util.FileCopyUtils.copy(in, out);
    }

    /**
     * 바이트 배열을 출력 스트림으로 복사한다.
     *
     * @param in  바이트 배열
     * @param out 출력 스트림
     * @throws java.io.IOException 입출력 에러 발생시
     */
    public static void copy(byte[] in, OutputStream out) throws IOException {
        org.springframework.util.FileCopyUtils.copy(in, out);
    }

    /**
     * 입력 스트림에서 로딩하여 바이트 배열로 복사한다.
     *
     * @param in 입력 스트림
     * @return 변환한 바이트 배열
     * @throws java.io.IOException 입출력 에러 발생시
     */
    public static byte[] copyToByteArray(InputStream in) throws IOException {
        return org.springframework.util.FileCopyUtils.copyToByteArray(in);
    }

    /**
     * {@link java.io.Reader}에서 읽은 내용을 {@link java.io.Writer}로 복사한다.
     *
     * @param in  문자를 읽어들일 {@link java.io.Reader}
     * @param out 문자를 기록할 {@link java.io.Writer}
     * @return 복사한 문자의 수
     * @throws java.io.IOException 입출력 에러 발생시
     */
    public static int copy(Reader in, Writer out) throws IOException {
        return org.springframework.util.FileCopyUtils.copy(in, out);
    }

    /**
     * 주어진 문자열을 {@link java.io.Writer}로 복사한다. 작업이 끝나면 {@link java.io.Writer}는 close된다.
     *
     * @param in  문자열
     * @param out 복사할 {@link java.io.Writer}
     * @throws java.io.IOException 입출력 에러 발생시
     */
    public static void copy(String in, Writer out) throws IOException {
        org.springframework.util.FileCopyUtils.copy(in, out);
    }

    /**
     * 지정한 {@link java.io.Reader}를 통해 읽어들인 내용을 문자열에 복사한다. 작업이 종료되면 Reader는 close된다.
     *
     * @param in 읽어들일 {@link java.io.Reader}
     * @return 읽어들인 문자열
     * @throws java.io.IOException 입출력 에러 발생시
     */
    public static String copyToString(Reader in) throws IOException {
        return org.springframework.util.FileCopyUtils.copyToString(in);
    }

    /**
     * 지정한 디렉토리의 모든 파일 및 디렉토리를 반환한다. 지정한 위치가 디렉토리가 아닌 경우 null을 반환한다.
     *
     * @param location 디렉토리
     * @return 파일 목록(디렉토리가 아닌 경우 null)
     */
    public static String[] getFileList(final String location) {
        File file = new File(location);
        if (file.isDirectory()) {
            return file.list();
        }
        return null;
    }

    /**
     * 지정한 위치와 파일의 확장자 명에 포함되는 모든 파일 목록을 반환한다. 지정한 위치가 디렉토리가 아닌 경우 null을 반환한다.
     *
     * @param location  디렉토리
     * @param extension 파일의 확장자
     * @return 파일 목록(디렉토리가 아닌 경우 null)
     */
    public static String[] getFileListByExtension(final String location, final String extension) {
        File file = new File(location);
        if (file.isDirectory()) {
            String[] lists = file.list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    if (name.endsWith(extension.toLowerCase()) || name.endsWith(extension.toUpperCase())) {
                        return true;
                    }
                    return false;
                }
            });
            return lists;
        }
        return null;
    }

    /**
     * 지정한 파일을 삭제한다.
     *
     * @param filename 파일명
     * @return 정상적으로 삭제되면 <tt>true</tt>를 반환한다. 디렉토리 이거나 파일을 지울 수 없는 경우 <tt>false</tt>를 반환한다.
     */
    public static boolean delete(final String filename) {
        File file = new File(filename);
        if (!file.isDirectory() && file.isFile()) {
            file.delete();
            if (!file.exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 지정한 파일의 최근 변경된 시간을 반환한다.
     *
     * @param filename 파일명
     * @return 변경된 시간. 만약에 파일이 존재하지 않거나 입출력 에러가 발생하면 0L을 반환한다.
     */
    public static long lastModified(final String filename) {
        return new File(filename).lastModified();
    }

    /**
     * 지정한 경로에서 파일명만 추출한다. 예) "mypath/myfile.txt" -> "myfile.txt".
     *
     * @param path 파일 경로(<tt>null</tt>이 될 수도 있음)
     * @return 추출된 파일명 또는 파일명이 없는 경우 <tt>null</tt>
     */
    public static String getFilename(String path) {
        return org.springframework.util.StringUtils.getFilename(path);
    }

    /**
     * 지정한 경로에서 파일의 확장자를 추출한다. 예) "mypath/myfile.txt" -> "txt".
     *
     * @param path 파일 경로(<tt>null</tt>이 될 수도 있음)
     * @return the extracted filename extension, or <tt>null</tt> if none
     */
    public static String getFilenameExtension(String path) {
        return org.springframework.util.StringUtils.getFilenameExtension(path);
    }

    /**
     * 지정한 경로에서 파일의 확장자를 제외한 경로를 반환한다. 예) "mypath/myfile.txt" -> "mypath/myfile".
     *
     * @param path 파일 경로(<tt>null</tt>이 될 수도 있음)
     * @return 확장자가 삭제된 파일의 경우 또는 파일이 없다면 <tt>null</tt>
     */
    public static String stripFilenameExtension(String path) {
        return org.springframework.util.StringUtils.stripFilenameExtension(path);
    }

    /**
     * 두 경로를 비교한다.
     *
     * @param path1 비교할 첫 번째 경로
     * @param path2 비교할 두 번째 경로
     * @return 더 경로가 동일하면 <tt>true</tt>
     */
    public static boolean pathEquals(String path1, String path2) {
        return org.springframework.util.StringUtils.pathEquals(path1, path2);
    }

    /**
     * 시스템 커맨드를 실행한다.
     *
     * @param command 시스템 커맨드(예; <tt>chmod 755 run.sh</tt>)
     */
    public static void executeCommand(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        int result = process.waitFor();
        if (result == 0) {
            logger.info("Command executed successfully");
        } else {
            logger.info("Failed to execute command");
        }
        String message = null;
        while ((message = in.readLine()) != null) {
            logger.info(message);
        }
    }

    /**
     * File에 내용을 추가 할 수 있는 PrintWriter 객체를 반환한다.
     * 기존 파일을 overwrite한다.
     * 마지막에 PrintWriter는 close되어야 한다.
     *
     * @param file 소스 파일
     * @return PrintWriter
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     *
     */
    public static PrintWriter getPrintWriter(File file) throws FileNotFoundException, UnsupportedEncodingException {
        return getPrintWriter(file, false);
    }

    /**
     * File에 내용을 추가 할 수 있는 PrintWriter 객체를 반환한다.
     * 기존 파일 내용 마지막에 추가한다.
     *
     * @param file 소스 파일
     * @return PrintWriter
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     *
     */
    public static PrintWriter getPrintWriterToAppend(File file) throws FileNotFoundException, UnsupportedEncodingException {
        return getPrintWriter(file, true);
    }

    /**
     * File에 내용을 추가 할 수 있는 PrintWriter 객체를 반환한다.
     *
     * @param file   소스 파일
     * @param append 내용 추가 여부
     * @return PrintWriter
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     *
     */
    public static PrintWriter getPrintWriter(File file, boolean append) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, append), "UTF-8"));
        return writer;
    }
}