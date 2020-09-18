package com.example.mytestapp.Activity.Model

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
class SummaryGeneratorTest {
    private lateinit var summaryGenerator: SummaryGenerator

    @Before
    fun setup() {
        summaryGenerator = SummaryGenerator()
    }

    @Test
    fun testGenerateTotal() {
        val total = 0
        val bigSummaryModel = BigSummaryModel(
            1
            , 1
            , "title"
            , "catName"
            , 1
            , "curatorName"
            , "iconUrl"
            , "imgUrl"
            , "videoUrl"
            , "tagName"
            , 0
            , 0
        )
        val expectedSummary = Summary(bigSummaryModel, total)
        val resultSummary = summaryGenerator.generateSummary(bigSummaryModel)
        assertEquals(expectedSummary, resultSummary)
    }
}