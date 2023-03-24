package rob.myappcompany.simpletodoapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd: Button = findViewById(R.id.btnAdd)
        val lv: ListView = findViewById(R.id.lv)

        // 1) アダプターに入れてListView にセット
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            mutableListOf() // 最初は空っぽのリスト// arrayListOf() でも動くよ
        )
        lv.adapter = adapter

        // 2) + ボタンでアラートダイアログを表示
        btnAdd.setOnClickListener {
            //3) EditText を newする(or 中身を入れる)
            val et = EditText(this) // R.id.~ ではなく、自分自身の thisを入れる

            AlertDialog.Builder(this)
                .setTitle("項目の追加")
                .setMessage("メモを書き込みましょう。")
                .setView(et)
                .setPositiveButton("追加") { _, _ ->
                    //4) add() でアダプターに追加する
                    val myTodo = et.text.toString()
                    adapter.add(myTodo)
                }
                .setNegativeButton("キャンセル", null)
                .show()
        }

        // 5) ListView をタッチしたらアラートダイアログ
        lv.setOnItemClickListener { _, _, i, _ ->
            AlertDialog.Builder(this)
                .setTitle("削除しますか？")
                // 6) removeで削除
                .setPositiveButton(
                    "はい",
                ) { _, _ -> // 使わないし, iが被るので_ アンスコ
                    adapter.remove(adapter.getItem(i)) // 配列がまだ空なときは、 ~.getItem(i) で添字取得
//                  adapter.notifyDataSetChanged() // 補足：画面を更新する呪文
                }
                .setNegativeButton("いいえ", null)
                .show()
        }
    }
}
