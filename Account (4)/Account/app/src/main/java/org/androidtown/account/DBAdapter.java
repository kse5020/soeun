package org.androidtown.account;

public class DBAdapter{

    String location;
    String like;
    String name;
    String place;
    String price;
    String date;

    public DBAdapter(String location, String like, String name, String place, String price, String date) {
        this.location = location;
        this.like = like;
        this.name = name;
        this.place = place;
        this.price = price;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}










    /*    Cursor cursor;


    public DBAdapter(Context context, Cursor c) {
        super(context, c);
        cursor = c;

    }


    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        final TextView location = (TextView)view.findViewById(R.id.location);
        final TextView like = (TextView)view.findViewById(R.id.like);
        final TextView name = (TextView)view.findViewById(R.id.name);
        final TextView price = (TextView)view.findViewById(R.id.price);
        final TextView place = (TextView)view.findViewById(R.id.place);
        final TextView date = (TextView)view.findViewById(R.id.date);

        location.setText(cursor.getString(cursor.getColumnIndex("locaation")));
        like.setText(cursor.getString(cursor.getColumnIndex("like")));
        name.setText(cursor.getString(cursor.getColumnIndex("name")));
        date.setText(cursor.getString(cursor.getColumnIndex("age")));
        price.setText(cursor.getString(cursor.getColumnIndex("price")));
        place.setText(cursor.getString(cursor.getColumnIndex("place")));

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.listlayout, parent, false);
        return v;
    }
}*/