package com.example.memorina

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class TilesView(ctx: Context) : GridLayout(ctx) {
    private val cardImages = mutableListOf(
        R.drawable.card_1, R.drawable.card_1,
        R.drawable.card_2, R.drawable.card_2,
        R.drawable.card_3, R.drawable.card_3,
        R.drawable.card_4, R.drawable.card_4,
        R.drawable.card_5, R.drawable.card_5,
        R.drawable.card_6, R.drawable.card_6,
        R.drawable.card_7, R.drawable.card_7,
        R.drawable.card_8, R.drawable.card_8
    )

    // Слушатель кликов по картам
    private val cardClickListener = View.OnClickListener { view ->
        if (view is ImageView) {
            flipCard(view)
        }
    }

    init {
        columnCount = 4
        rowCount = 4
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        cardImages.shuffle()
        val displayMetrics = DisplayMetrics()
        (context as AppCompatActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels
        val cardSize = (screenWidth - 60) / 4
        val cardMargin = 20
        for (rowIndex in 0 until 4) {
            for (columnIndex in 0 until 4) {
                val cardIndex = rowIndex * 4 + columnIndex
                val cardView = ImageView(context).apply {
                    setImageResource(R.drawable.card_back)
                    val params = GridLayout.LayoutParams().apply {
                        rowSpec = GridLayout.spec(rowIndex)
                        columnSpec = GridLayout.spec(columnIndex)
                        width = cardSize
                        height = cardSize
                        // Добавляем отступы между картами
                        if (columnIndex != 0) {
                            leftMargin = cardMargin
                        }
                        if (rowIndex != 0) {
                            topMargin = cardMargin
                        }
                    }
                    layoutParams = params
                    tag = cardImages[cardIndex]
                    setOnClickListener(cardClickListener)
                }
                addView(cardView)
            }
        }
    }

    private fun flipCard(card: ImageView) {
        val frontImage = card.tag as Int
        card.setImageResource(frontImage)
        postDelayed({
            card.setImageResource(R.drawable.card_back)
        }, 1000)
    }
}