package vn.hailt.lab3.lt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vn.hailt.lab3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetTask getTask = new GetTask();
                getTask.execute("http://asian.dotplays.com/wp-json/wp/v2/posts?embed");
                getTask.setListener(new ITaskFinishedListener() {
                    @Override
                    public void onFinished(String result) {

//                        List<Post> posts = new ArrayList<>();
//
//                        try {
//                            JSONArray root = new JSONArray(result);
//
//                            Log.e("TAG", "onFinished: " + root.length());
//
//                            for (int i = 0; i < root.length(); i++) {
//                                JSONObject post = root.getJSONObject(i);
//
//                                int id = post.getInt("id");
//                                String date = post.getString("date");
//
//                                JSONObject postTitle = post.getJSONObject("title");
//                                String title = postTitle.getString("rendered");
//
//                                Log.e("TAG", "onFinished: " + id + " | " + date + " | " + title);
//
//                                Post postModel = new Post();
//                                postModel.id = id;
//                                postModel.date = date;
//                                postModel.title = title;
//                                posts.add(postModel);
//                            }
//
//                            Log.e("TAG", "onFinished: posts size " + posts.size());
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

//                        JsonParser jsonParser = new JsonParser();
//                        JsonElement jsonElement = jsonParser.parse(result);
//
//                        JsonArray root = jsonElement.getAsJsonArray();
//                        for (int i = 0; i < root.size(); i++) {
//
//                            JsonObject post = root.get(i).getAsJsonObject();
//                            int id = post.get("id").getAsInt();
//                            String date = post.get("date").getAsString();
//
//                            JsonObject postTitle = post.get("title").getAsJsonObject();
//                            String title = postTitle.get("rendered").getAsString();
//
//                            Log.e("TAG", "onFinished: " + id + " | " + date + " | " + title);
//                        }
                    }
                });
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
