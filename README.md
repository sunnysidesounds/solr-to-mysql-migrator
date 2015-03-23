# Solr To MySQL Migration Tool

This simple web service built with the Play! Framework can migrate indexed Solr data to a MySQL Datasource. 


## Setup

1. Confirm you have `"mysql" % "mysql-connector-java" % "<your_mysql_version_number>"` in your **appDependencies** in ./solr-to-mysql-migrator/project/Build.scala

2. Create a database called **solr_mysql_raw** and run the **create_solr_mysql_raw.sql** sql script

3. Change **example-application.conf** to **application.conf**

4. In the application.conf change the following values to suit your needs:

        # Update database values:
        db.default.user= <your_username>
        db.default.password= <your_password>


        # Update your solr base url (in double-quotes):
        solr.base.url= <base_url>


        # Update your solr query (in double-quotes and url encode your query):
        solr.query=<your_solr_query>


        # Update your solr format (in double-quotes):
        solr.format = <your_format>


        # Update solr start location, default is 0 :
        solr.start = 0


        # Update solr rows, how many rows from your query you want to return. Default is 1000 :
        solr.rows = 1000

5. Next start up your Solr instance, I simple did this

        cd solr/example/

        java -jar start.jar


6. Finally let's start up our Play! app

        # Note use any port number you'd, like
        play clean compile "run 9011"


## Running

1. Simply cURL the import endpoint

        curl -X GET http://localhost:9011/import


