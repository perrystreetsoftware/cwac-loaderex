CWAC LoaderEx: Taking Loaders to the Next Level
===============================================

Android 3.0 introduced the `Loader` framework, and the
Android Compatibility Library allows you to use that
framework going back to Android 1.6. However, the only
supplied concrete implementation of a `Loader` is
`CursorLoader`, and that is only for use with a
`ContentProvider`. Moreover, while the `Loader` framework
handles database queries in the background, it does not
help with the rest of your CRUD operations.

This `LoaderEx` project is designed to help fill some
of those gaps. Presently, it provides a `SQLiteCursorLoader`,
offering the same basic concept as `CursorLoader`, but
for use with a `SQLiteDatabase` instead of a `ContentProvider`.

This is packaged as an Android library project, though a simple
JAR is also available from the Downloads section of this
GitHub repository. If you are working on a native Honeycomb
application (i.e., not using the Android Compatibility
Library), please use the JAR &mdash; put it in your project's
`libs/` directory and, if you are using Eclipse, add it to
your build path.

Usage
-----
Generally speaking, you use `SQLiteCursorLoader` in the same
fashion as you would use `CursorLoader` &mdash; by having your
activity implement `LoaderManager.LoaderCallbacks<Cursor>`
and calling `initLoader()` on the `LoaderManager`. Then, in
your `onCreateLoader()` callback method, you can return a
properly-constructed `SQLiteCursorLoader`. Everything else
behaves as `CursorLoader` does.

### Constructors

There is only one at this time, taking a `SQLiteDatabase`
object, plus the same parameters as is used by `rawQuery()`
on `SQLiteDatabase` &mdash; a `String` with your SQL query
and a `String[]` of positional parameter values (to replace
any `?` you have in your query).

### Packages

There are two implementations of `SQLiteCursorLoader`, in
two separate packages.

The one in `com.commonsware.cwac.loaderex` works using
the native API Level 11+ implementation of the `Loader`
framework.

The one in `com.commonsware.cwac.loaderex.acl` works
using the implementation of the `Loader` framework from
the Android Compatibility Library (ACL). You will need to
have the ACL as part of your build path in addition to having
the JAR or library project of `LoaderEx`.

In your code, you will choose the one you wish to use
based upon whether you are using the ACL or not.

### AbstractCursorLoader

`SQLiteCursorLoader` itself extends an `AbstractCursorLoader`.
`AbstractCursorLoader` is much of the logic from the ACL's
`CursorLoader`, but with the actual query code abstracted
out. You are welcome to make your own subclasses of
`AbstractCursorLoader` if you are creating `Cursor`s from
other sources. Just override `buildCursor()` and have it
return the `Cursor` &mdash; this method is called on a
background thread and therefore is not time-limited.

Dependencies
------------
This project sometimes depends on the Android Compatibility
Library (ACL). If you are using it in source form as an Android
library project, you will need the ACL. If you are using the
JAR, you only need the ACL if you are using the `.acl`
editions of the classes. 

Version
-------
This is version v0.1 of this module, meaning that it is
fresh out of the box.

Demo
----
In the `demo/` sub-project you will find
a sample activity that demonstrates the use of `SQLiteCursorLoader`.

Note that when you build the JAR via `ant jar`, the sample
activity is not included, nor any resources -- only the
compiled classes for the actual library are put into the JAR.

Future
------
Future editions of this project will add things like:

 - Support for `query()` in addition to `rawQuery()`-style queries
 - Support for synchronization on the `SQLiteDatabase`
 - Separate task classes for performing other CRUD operations on databases and content providers in background threads

License
-------
The code in this project is licensed under the Apache
Software License 2.0, per the terms of the included LICENSE
file.

Questions
---------
If you have questions regarding the use of this code, please post a question
on [StackOverflow](http://stackoverflow.com/questions/ask) tagged with `commonsware` and `android`. Be sure to indicate
what CWAC module you are having issues with, and be sure to include source code 
and stack traces if you are encountering crashes.

Release Notes
-------------
v0.1.0: initial release

Who Made This?
--------------
<a href="http://commonsware.com">![CommonsWare](http://commonsware.com/images/logo.png)</a>

