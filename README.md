# Covid19 DGS

This is a [Kotlin][0]/[Spring][1] application using [DGS][2] and [SQLDelight][3] that exposes the data from the 
New York Times [Coronavirus (Covid-19) Data in the United States][4] data set. This is “Data from The New York Times, 
based on reports from state and local health agencies.” For more information, see their [tracking page][5].

The latest version should always be deployed at https://murmuring-badlands-87610.herokuapp.com/graphiql

Data sets currently supported:

- [x] Counties (Historical)
- [ ] States (Historical)
- [ ] US (Historical)
  
- [ ] Counties (Live)
- [ ] States (Live)
- [ ] US (Live)

- [ ] Colleges

## Main components

### CSV Data

Data is fetched using [Spring Batch][7], which periodically pulls down the appropriate CSV file, parses it and populates
a table. Items are upserted using `ON CONFLICT DO UPDATE SET ...` to handle and rows that have
had their numbers adjusted since the previous update to the file.

### Database

Tables are currently in memory in SQLite, using SQLDelight, possibly moving to Postgres.

### GraphQL

The GraphQL server is DGS, using a fairly basic set of queries for each dataset that allows filtering
on string fields, date ranges and numeric elements. 

### Warning, Disclaimer, etc.

This is not my data. I do not work for The New York Times. I do not have any control over the data itself, 
nor can I vouch for its accuracy. If you have any questions or concerns with the data please see the 
contact info in their GitHub [repository][6].


[0]: https://kotlinlang.org
[1]: https://spring.io
[2]: https://netflix.github.io/dgs/
[3]: https://cashapp.github.io/sqldelight/
[4]: https://github.com/nytimes/covid-19-data
[5]: https://www.nytimes.com/interactive/2020/us/coronavirus-us-cases.html
[6]: https://github.com/nytimes/covid-19-data
[7]: https://spring.io/projects/spring-batch
