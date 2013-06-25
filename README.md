Haroun
======

A collection of the code snippets I find myself continually re-writing.

The name `Haroun` comes from the children's book
[Haroun and the Sea of Stories][1] and is also the name of [my cat][2].

`Files`
-------
A no-holds-barred utility for reading files into memory.  And by
"no-holds-barred", I mean no error checking whatsoever (almost to the point of
absurdity).

Apparently this is [built in to Java 7][3], but some of us are stuck with
Java <= 6.

`URLGetter`
-----------
Fetch a given URL using the given method (and optionally sending the given
request body).

It was initially created as a way to quickly test the `http.proxyHost` and
`http.proxyPort` JVM settings, which may be used as follows:

```bash
export PROXY_HOST=www.example.com
export PROXY_PORT=9999
java -Dhttp.proxyHost=$PROXY_HOST -Dhttp.proxyPort=$PROXY_PORT URLGetter post http://localhost:8080/foo ./testbody.txt
```

[1]: http://www.goodreads.com/book/show/4835.Haroun_And_The_Sea_Of_Stories "Haroun and the Sea of Stories"
[2]: http://i.imgur.com/B890pqn.jpg "Haroun"
[3]: http://docs.oracle.com/javase/7/docs/api/java/nio/file/Files.html "Files in Java 7"
