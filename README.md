# Solr To MySQL Migration Tool

This simple web service built with the Play! Framework can migrate indexed Solr data to a MySQL Datasource. 


## Setup

- STEP 1: Add `"mysql" % "mysql-connector-java" % "<your_mysql_version_number>"` to your **appDependencies** in ./solr-to-mysql-migrator/project/Build.scala

- STEP 2: Create a database called **solr_mysql_raw** and run the **create_solr_mysql_raw.sql** sql script

- STEP 3: Change **example-application.conf** to **application.conf**

- STEP 4: In the application.conf add your database username and password into these fields:

        db.default.user=<your_username>
        db.default.password=<your_password>

