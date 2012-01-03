/*
 * Copyright (c) 2011-2012 CommonsWare, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.commonsware.cwac.loaderex.acl;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteCursorLoader extends AbstractCursorLoader {
  SQLiteDatabase db=null;
  String rawQuery=null;
  String[] args=null;

  /**
   * Creates a fully-specified SQLiteCursorLoader. See
   * {@link SQLiteDatabase#rawQuery(SQLiteDatabase, String, String[])
   * SQLiteDatabase.rawQuery()} for documentation on the
   * meaning of the parameters. These will be passed as-is
   * to that call.
   */
  public SQLiteCursorLoader(Context context, SQLiteDatabase db,
                            String rawQuery, String[] args) {
    super(context);
    this.db=db;
    this.rawQuery=rawQuery;
    this.args=args;
  }

  /**
   * Runs on a worker thread and performs the actual database
   * query to retrieve the Cursor.
   */
  @Override
  protected Cursor buildCursor() {
    return(db.rawQuery(rawQuery, args));
  }

  /**
   * Writes a semi-user-readable roster of contents to
   * supplied output.
   */
  @Override
  public void dump(String prefix, FileDescriptor fd,
                   PrintWriter writer, String[] args) {
    super.dump(prefix, fd, writer, args);
    writer.print(prefix);
    writer.print("rawQuery=");
    writer.println(rawQuery);
    writer.print(prefix);
    writer.print("args=");
    writer.println(Arrays.toString(args));
  }
}