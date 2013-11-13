package com.example.androidpos.domian;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper implements
		DatabaseHandler {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "POS_DATABASE";

	public SQLiteDatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public Product selectProduct(String _id) {
		try {
			String[] data = null;

			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.query(CATALOG_TABLE_NAME,
					new String[] { "*" }, "_id = ?",
					new String[] { String.valueOf(_id) }, null, null, null);

			if (cursor != null)
				if (cursor.moveToFirst()) {
					data = new String[cursor.getColumnCount()];
					for (int i = 0; i < data.length; i++)
						data[i] = cursor.getString(i);
				}

			/** Close database and cursor. */
			cursor.close();
			db.close();
			
			Product product = new Product(data);
			return product;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String[] selectStock(String name) {
		try {
			String[] data = null;

			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.query(STOCK_TABLE_NAME,
					new String[] { "*" }, "name = ?",
					new String[] { String.valueOf(name) }, null, null, null);

			if (cursor != null)
				if (cursor.moveToFirst()) {
					data = new String[cursor.getColumnCount()];
					for (int i = 0; i < data.length; i++) {
						data[i] = cursor.getString(i);
					}
				}

			/** Close database and cursor. */
			cursor.close();
			db.close();
			return data;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String[][] selectAll(String tableName) {

		try {
			String[][] data = null;

			SQLiteDatabase db = this.getReadableDatabase();

			String sql = "SELECT * FROM " + tableName;
			Cursor cursor = db.rawQuery(sql, null);

			int row = 5;

			if (cursor != null)
				if (cursor.moveToFirst()) {
					data = new String[cursor.getCount()][row];
					int i = 0;
					do {
						for (int j = 0; j < row; j++)
							data[i][j] = cursor.getString(j);
						i++;
					} while (cursor.moveToNext());
				}

			/** Close database and cursor. */
			cursor.close();
			db.close();
			return data;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public long insertProduct(Product product) {
		try {
			/** Get database. */
			SQLiteDatabase db = this.getWritableDatabase();

			/** Prepare values to insert. */
			ContentValues values = new ContentValues();
			values.put("_id", product.getId() );
			values.put("name", product.getName() );
			values.put("tag", product.getTag() );
			values.put("last_edit", product.getLastEdit() );
			values.put("price", product.getPrice() );

			/** Insert values to database. */
			long rows = db.insert(CATALOG_TABLE_NAME, null, values);

			/** Close database. */
			db.close();
			return rows;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public long insertStock(Item item) {
		try {
			/** Get database. */
			SQLiteDatabase db = this.getWritableDatabase();

			/** Prepare values to insert. */
			ContentValues values = new ContentValues();
			values.put("_id", item.getId());
			values.put("name", item.getName());
			values.put("quantity", item.getQuantity());
			values.put("cost", item.getCost());
			values.put("last_edit", item.getLastEdit());

			/** Insert values to database. */
			long rows = db.insert(STOCK_TABLE_NAME, null, values);

			/** Close database. */
			db.close();
			return rows;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public long updateProduct(Product product) {
		try {
			/** Get database. */
			SQLiteDatabase db = this.getWritableDatabase();

			/** Prepare values to insert. */
			ContentValues values = new ContentValues();
			values.put("_id", product.getId() );
			values.put("price", product.getPrice() );
			values.put("tag", product.getTag() );
			values.put("last_edit", product.getLastEdit());

			long rows = db.update(CATALOG_TABLE_NAME, values, "_id = ?",
					new String[] { String.valueOf( product.getId() ) });

			/** Close database. */
			db.close();
			return rows;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public long updateStock(String name, String STOCK, String unit,
			double price, double cost) {
		try {
			/** Get database. */
			SQLiteDatabase db = this.getWritableDatabase();

			/** Prepare values to insert. */
			ContentValues values = new ContentValues();
			values.put("name", name);
			values.put("STOCK", STOCK);
			values.put("unit", unit);
			values.put("price", price);
			values.put("cost", cost);

			long rows = db.update(CATALOG_TABLE_NAME, values, "name = ?",
					new String[] { String.valueOf(name) });

			/** Close database. */
			db.close();
			return rows;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public long updateStock(String name, int quantity) {
		try {
			/** Get database. */
			SQLiteDatabase db = this.getWritableDatabase();

			/** Prepare values to insert. */
			ContentValues values = new ContentValues();
			values.put("name", name);
			values.put("quantity", quantity);

			long rows = db.update(STOCK_TABLE_NAME, values, "name = ?",
					new String[] { String.valueOf(name) });

			/** Close database. */
			db.close();
			return rows;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public long deleteProduct(String _id) {
		try {
			/** Get database. */
			SQLiteDatabase db = this.getWritableDatabase();

			long rows = db.delete(CATALOG_TABLE_NAME, "_id = ?",
					new String[] { String.valueOf(_id) });

			/** Close database. */
			db.close();
			return rows;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public long deleteStock(String name) {
		try {
			/** Get database. */
			SQLiteDatabase db = this.getWritableDatabase();

			long rows = db.delete(STOCK_TABLE_NAME, "name = ?",
					new String[] { String.valueOf(name) });

			/** Close database. */
			db.close();
			return rows;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + CATALOG_TABLE_NAME
				+ "( _id INTEGER PRIMARY KEY," 
				+ " name TEXT(100)," 
				+ " price DOUBLE,"
				+ " tag TEXT(100)," 
				+ " last_edit TEXT(100));");
		Log.d("CREATE CATALOG TABLE", "Success");
		db.execSQL("CREATE TABLE " + STOCK_TABLE_NAME
				+ " ( _id INTEGER,"
				+ " name TEXT(100),"
				+ " quantity INTEGER," 
				+ " last_edit TEXT(100)," 
				+ " cost DOUBLE);");
		Log.d("CREATE STOCK TABLE", "Success");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
