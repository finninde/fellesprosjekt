sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password password wawwwawiwa'
sudo debconf-set-selections <<< 'mysql-server-5.5 mysql-server/root_password_again password wawwwawiwa'


sudo apt-get update 
sudo apt-get install -y vim virtualenvwrapper git screen libjpeg8 libjpeg8-dev zlib-bin libtiff4 libtiff4-dev libfreetype6 libfreetype6-dev libjpeg-dev libpng-dev tmux mysql-server


if [ ! -f /var/log/databasesetup ];
then
    echo "CREATE USER 'calendar'@'localhost' IDENTIFIED BY 'wawwwawiwa'" | mysql -uroot -pwawwwawiwa
    echo "CREATE DATABASE calendar" | mysql -uroot -pwawwwawiwa
    echo "GRANT ALL ON calendar.* TO 'calendar'@'%' IDENTIFIED BY 'wawwwawiwa'" | mysql -uroot -pwawwwawiwa
    echo "GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'wawwwawiwa'" | mysql -uroot -pwawwwawiwa
    echo "flush privileges" | mysql -uroot -pwawwwawiwa

    touch /var/log/databasesetup

    if [ -f /vagrant/db2/db.sql ];
    then
        mysql -uroot -pwawwwawiwa calendar < /vagrant/db2/db.sql
    fi
    sudo sed -i s/127.0.0.1/0.0.0.0/g /etc/mysql/my.cnf
    sudo service mysql restart
fi



