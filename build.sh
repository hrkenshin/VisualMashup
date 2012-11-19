#!bin/sh

profile=$1
if [ -z "$1" ];then
    profile="release"
fi

echo -e "\n##### source update .....\n"
svn update

echo -e "\n##### maven build .....\n"
mvn -Dmaven.test.skip=true -Dmode=$profile clean package