package com.example.kmn_v2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Масив объектов и рандом
        val masiv = arrayOf("Камень","Ножницы","Бумага")
        var random = masiv.random()

        //Объявление кнопок
        val stone:Button = findViewById(R.id.stone)
        val paper:Button = findViewById(R.id.paper)
        val noj:Button = findViewById(R.id.noj)

        // Кнопка Играть
        val game:Button = findViewById(R.id.game)

        // Все связанное с балансом
        var balance = 500
        val balance_textview:TextView = findViewById(R.id.balance)
        balance_textview.text = "Баланс ${balance.toString()}"

        //textview результат
        val resultat:TextView = findViewById(R.id.result)

        // TextView выбор пользователя и переменная
        val pick_textview:TextView = findViewById(R.id.vibor)
        var pick = ""

        // Все связанное со ставкой
        val stavka_button:Button = findViewById(R.id.Stavka)
        val stavka_EditText:EditText = findViewById(R.id.StavkaEditText)
        val stavka_TextView:TextView = findViewById(R.id.stavkaTextView)
        var stavka = 0

        // Кнопка ставки
        stavka_button.setOnClickListener {
            if (stavka_EditText.text.toString() == "") {
                Toast.makeText(this, "Сделайте ставку", Toast.LENGTH_SHORT).show()
            }else {

                // Создание переменной считывающей EditText, и записывающей текст
                var pick_stavka = Integer.parseInt(stavka_EditText.text.toString())

                if (pick_stavka > balance) {
                    Toast.makeText(this, "Ваша ставка привышает ваш баланс", Toast.LENGTH_SHORT).show()
                }
                else if (balance == 0) {
                    Toast.makeText(this, "Ваш баланс равен нулю", Toast.LENGTH_SHORT).show()
                }
                else {
                    balance = balance - pick_stavka
                    balance_textview.text = "Баланс ${balance.toString()}"
                    stavka = pick_stavka
                    stavka_TextView.text = "Ваша ставка $stavka"

                    // Блокировка кнопки сделать ставку и EditText
                    stavka_EditText.setEnabled(false)
                    stavka_button.setEnabled(false)
                }
            }
        }

        // Выбор пользователя
        stone.setOnClickListener {
            pick = "Камень"
            pick_textview.text = "Вы выбрали $pick"
        }
        paper.setOnClickListener {
            pick = "Бумага"
            pick_textview.text = "Вы выбрали Бумагу"
        }
        noj.setOnClickListener {
            pick = "Ножницы"
            pick_textview.text = "Вы выбрали $pick"
        }

        // Кнопка играть
        game.setOnClickListener {
            if (pick_textview.text.toString() == "" && stavka_EditText.text.toString() == "") {
                Toast.makeText(this, "Вы не сделали выбор и не поставили ставку", Toast.LENGTH_SHORT).show()
            }
            else if (stavka_EditText.text.toString() == "") {
                Toast.makeText(this, "Вы не поставили ставку", Toast.LENGTH_SHORT).show()
            }
            else if (pick_textview.text.toString() == "") {
                Toast.makeText(this, "Вы не сделали выбор", Toast.LENGTH_SHORT).show()
            }
            else {
                //Вызываем рандом
                random = masiv.random()
                // Сверяем
                if ((random == "Ножницы") && (pick == "Камень")) {
                    var a:Int = (stavka * 2.45).toInt()
                    balance = balance + a
                    balance_textview.text = "Баланс ${balance.toString()}"
                    resultat.text = "Вы выйграли $a"
                    balance_textview.text = "Баланс ${balance.toString()}"
                }
                else if ((random == "Камень") && (pick == "Камень")){
                    resultat.text = "Ничья"
                    balance = balance + stavka
                    balance_textview.text = "Баланс ${balance.toString()}"
                }
                else if ((random == "Бумага") && (pick == "Камень")) {
                    resultat.text = "Вы проиграли"
                    balance_textview.text = "Баланс ${balance.toString()}"
                }
                else if ((random == "Бумага") && (pick == "Ножницы")) {
                    var a:Int = (stavka * 2.45).toInt()
                    balance = balance + a
                    balance_textview.text = "Баланс ${balance.toString()}"
                    resultat.text = "Вы выйграли $a"
                    balance_textview.text = "Баланс ${balance.toString()}"
                }
                else if ((random == "Ножницы") && (pick == "Ножницы")){
                    resultat.text = "Ничья"
                    balance = balance + stavka
                    balance_textview.text = "Баланс ${balance.toString()}"
                }
                else if ((random == "Камень") && (pick == "Ножницы")) {
                    resultat.text = "Вы проиграли"
                    balance_textview.text = "Баланс ${balance.toString()}"
                }
                else if (random == "Камень" && pick == "Бумага") {
                    var a:Int = (stavka * 2.45).toInt()
                    balance = balance + a
                    balance_textview.text = "Баланс ${balance.toString()}"
                    resultat.text = "Вы выйграли $a"
                    balance_textview.text = "Баланс ${balance.toString()}"
                }
                else if (random == "Бумага" && pick == "Бумага"){
                    resultat.text = "Ничья"
                    balance = balance + stavka
                    balance_textview.text = "Баланс ${balance.toString()}"
                }
                else if (random == "Ножницы" && pick == "Бумага") {
                    resultat.text = "Вы проиграли"
                    balance_textview.text = "Баланс ${balance.toString()}"
                }
                // Делаем проверку на нулевой баланс, из-за чего блокируем все кнопки
                if (balance == 0) {
                    resultat.text = "Игра окончена"
                    stavka_EditText.setEnabled(false)
                    stavka_button.setEnabled(false)
                    paper.setEnabled(false)
                    stone.setEnabled(false)
                    noj.setEnabled(false)
                    game.setEnabled(false)

                    stavka_TextView.text = "Вы проиграли"
                    pick_textview.text = "Ваш баланс равен 0"
                }
                else {
                    // Открываем возможность взаимодейстия с кнопкой и edittext
                    stavka_EditText.setEnabled(true)
                    stavka_button.setEnabled(true)
                    // Приводим их в исходный вид
                    stavka_TextView.text = ""
                    pick_textview.text = ""
                }
            }
        }
    }
}