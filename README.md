# S2CX
S2CX is an approach to use SQL/XML queries to directly generate compressed XML from relational databases. The naïve approach would be to execute the SQL/XML query and compress the result. Our approach on the other hand extracts the XML structure from the SQL/XML query, creates an SQL query which retrieves the same data and generates events to create compressed XML or to just process the XML result (similar to SAX events). In this repository, we publish the code for the event generation. The event handlers for creating the compressed XML are not included in this repository, and we do not intend to publish code for this part of the project. However, the way the event handlers work is described in our publications. The S2CX approach has been developed at the research group for [Electronic Commerce and Databases](http://www-old.cs.uni-paderborn.de/en/research-group/ag-boettcher.html) at the [Department of Computer Science](http://www.cs.upb.de) of the [University of Paderborn](http://www.uni-paderborn.de). For more information on the approach, please have a look at our publications.

Please be aware that this is a prototypic implementation that comes with absolutely no warranty. We have successfully used the library with Oracle XE, DB2, and SQL Server. Our approach should basically work with any SQL-based database system. However, we know that this library does not work with all SQL-based databases systems because even though SQL is standardized, there are some differences in the SQL support (see Limitations and Assumptions).

## Getting started
We provide an example how to use the library in the [s2cx-example repository](https://github.com/dwolters/s2cx-example). We did not include the JDBC drivers for the databases. If you want to use a certain database, you have to download the driver and add it to the build path. You can find the driver here:
-	Oracle: http://www.oracle.com/technetwork/database/enterprise-edition/jdbc-111060-084321.html
-	DB2: http://www-01.ibm.com/support/docview.wss?uid=swg21363866
-	SQL Server: http://jtds.sourceforge.net

The [Trove]( http://trove.starlight-systems.com/) library in the lib folder has to be added to the build path as well.

## Limitations and Assumptions:
* The Parser only supports a subset of the SQL syntax. Hence, not all types of queries are supported by the library. For instance, to simplify the processing, we assumed that every table or column is enclosed by double quotes. Furthermore, a unique alias must be defined for every table and column reference must have the form "tablealias". "column". 
* Since the goal of the approach was to generate compressed XML, we assume that the SQL/XML query returns a single result which contains well-formed XML. The aggregation of multiple results can be done with XMLAGG.
* Even though JDBC should abstract from the database engine, not all database systems are the same. For instance, for Oracle, null is the highest value, however, for MySQL, it is the lowest. There are ways of coping with the differences, but we have not implemented them all. 


## Acknowledgments
The S2CX-library uses [Trove]( http://trove.starlight-systems.com/) (licensed under [LGPL](http://www.gnu.org/licenses/lgpl-2.1.html)) as a replacement for Java collections and the SQL/XML parser is created with [JavaCC]( https://javacc.java.net/). 


## Publications
* Stefan Böttcher, Dennis Bokermann, Rita Hartel: _Generalizing and Improving SQL/XML Query Evaluation_. The 8th International Conference on Signal Image Technology and Internet Systems (SITIS-2012), Sorrento, Italy, November 2012.
* Stefan Böttcher, Dennis Bokermann, Rita Hartel: _Computing compressed XML data from relational Databases_. 28th British National Conference on Databases (BNCOD-2011). Manchester, Great Britain, July 2011.
