package com.bikcodeh.borutoapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import com.bikcodeh.borutoapp.ui.theme.SMALL_PADDING
import org.junit.Rule
import org.junit.Test

class RatingWidgetStarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun passZeroPointZeroValue_Expect_FiveEmptyStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all = SMALL_PADDING), rating = 0.0)
        }

        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
    }

    @Test
    fun passFivePointZeroValue_Expect_FiveFilledStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all = SMALL_PADDING), rating = 0.0)
        }

        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(5)
    }

    @Test
    fun passThreePointZeroValue_Expect_ThreeFilledStarsAndTwoEmptyStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all = SMALL_PADDING), rating = 3.0)
        }

        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(2)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(3)
    }

    @Test
    fun passThreePointFourValue_Expect_TwoFilledStarsAndOneHalfFilledStarsAndOneEmptyStar() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all = SMALL_PADDING), rating = 3.4)
        }

        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(1)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(1)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(3)
    }

    @Test
    fun passNegativeValue_Expect_FiveEmptyStars() {
        composeTestRule.setContent {
            RatingWidget(modifier = Modifier.padding(all = SMALL_PADDING), rating = -3.4)
        }

        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
    }
}