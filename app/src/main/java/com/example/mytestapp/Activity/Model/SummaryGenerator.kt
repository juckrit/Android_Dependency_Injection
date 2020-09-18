package com.example.mytestapp.Activity.Model

class SummaryGenerator {

        fun generateSummary(bigSummaryModel:BigSummaryModel):Summary{
            val total = bigSummaryModel.like + bigSummaryModel.view
            return  Summary(bigSummaryModel,total)
        }

}