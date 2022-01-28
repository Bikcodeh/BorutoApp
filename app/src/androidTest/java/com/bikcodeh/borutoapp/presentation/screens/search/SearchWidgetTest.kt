package com.bikcodeh.borutoapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputText_assertInputText() {

        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onSearchClicked = {

                },
                onCloseClicked = {

                }
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Bikcode")
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextEquals("Bikcode")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButtonOnce_assertEmptyInputText() {

        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = {
                    text.value = it
                },
                onSearchClicked = {

                },
                onCloseClicked = {

                }
            )
        }
        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Bikcode")
        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()
        composeTestRule.onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }

    @Test
    fun openSearchWidget_addInputText_pressCloseButtonTwice_assertClosedState() {

        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)

        composeTestRule.setContent {
            if (searchWidgetShown.value) {
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onSearchClicked = {
                    },
                    onCloseClicked = {
                        searchWidgetShown.value = false
                    }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("TextField")
            .performTextInput("Bikcode")
        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()
        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()
        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }

    @Test
    fun openSearchWidget_pressCloseIconOnceWhenInputIsEmpty_assertClosedState() {

        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)

        composeTestRule.setContent {
            if (searchWidgetShown.value) {
                SearchWidget(
                    text = text.value,
                    onTextChange = {
                        text.value = it
                    },
                    onSearchClicked = {
                    },
                    onCloseClicked = {
                        searchWidgetShown.value = false
                    }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("CloseIcon")
            .performClick()
        composeTestRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }
}