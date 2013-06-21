haroun
======

A collection of the code snippets I find myself continually re-writing.

`Files`
-------
A no-holds-barred utility for reading files into memory.  And by
"no-holds-barred", I mean no error checking whatsoever (almost to the point of
absurdity).

Apparently this is built in to [Java 7](http://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html), but some of us are stuck with Java <= 6.

`URLGetter`
-----------
Fetch a given URL using the given method (and optionally sending the given
request body).
