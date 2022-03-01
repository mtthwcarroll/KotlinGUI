import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

fun main() = Window {
    //vals that compose "remembers" and will update anything that
    //uses them if they are changed.
    val listItems = remember { mutableStateListOf<String>() }
    val textFieldValue = remember { mutableStateOf(TextFieldValue())}
    val editing = remember { mutableStateOf(false) }

    //material theme contains everything. Not 100% EXACTLY what it is though.
    MaterialTheme {
        //scaffold is used for top and bottom bars
        Scaffold(
            //top bar parameter for scaffold = {TopAppBar()}. A little confusing of a format.
            topBar = {
                TopAppBar(
                    //parameters for TopAppBar
                    title = {
                        Text("An appbar")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                println("pressed button")
                            }
                        ) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                editing.value = !editing.value
                            }
                        ) {
                            Icon(Icons.Filled.Create, contentDescription = null)
                        }
                        IconButton(
                            onClick = {
                                if(textFieldValue.value.text.isNotEmpty()) {
                                    listItems.add(textFieldValue.value.text)
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = null)
                        }
                    }
                )
            },
            //BottomAppBar for the scaffold. can have stuff I just didn't put any
            bottomBar = {
                BottomAppBar(

                ) {

                }
            }
        //end scaffold constructor, begin content ( Scaffold (*where we were*) {*where we are*} )
        ) {
            //column seems like a generic structure
            Column(
                Modifier
                    .fillMaxWidth()
            ) {
                //text field for adding new stuff to our list
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    singleLine = true,
                    value = textFieldValue.value,
                    onValueChange = {
                        textFieldValue.value = it
                    }
                )
                //the column for our list
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    //not too sure about this construction format
                    itemsIndexed(listItems) {
                            index, item ->

                        //row, another generic structure
                        Row(

                        ) {
                            //this stuff is what we see get added
                            Text(text = item, Modifier.weight(.9f))

                            if(editing.value) {
                                ClickableText(
                                    text = AnnotatedString(text = "Delete"),
                                    onClick = {
                                        listItems.remove(item)
                                    },
                                    modifier = Modifier.weight(.1f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}