package app.harshit.smartreply

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.smartreply.FirebaseTextMessage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val messages = arrayListOf<FirebaseTextMessage>()
    private val messageAdapter = MessageAdapter(messages)
    private val suggestions = arrayListOf<String>()
    private val suggestionAdapter = SuggestionAdapter(suggestions)
    private val smartReply = FirebaseNaturalLanguage.getInstance().smartReply

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMessages.layoutManager = LinearLayoutManager(this)
        rvMessages.adapter = messageAdapter

        rvSuggestions.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        rvSuggestions.adapter = suggestionAdapter

        floatingActionButton.setOnClickListener {

            //The createForRemoteUser() method is to be used when the message was not sent by the user
            val message = FirebaseTextMessage.createForRemoteUser(
                editText.text.toString(), //Content of the message
                System.currentTimeMillis(), //Time at which the message was sent
                "uid_of_friend" //This has to be unique for every other person involved in the chat who is not your user
            )

            messages.add(
                message
            )

            messageAdapter.notifyItemInserted(messages.size)
            rvMessages.scrollToPosition(messages.size - 1)

            editText.setText("")

            smartReply.suggestReplies(
                messages.takeLast(2)
            )
                .addOnSuccessListener {
                    suggestions.clear()
                    it.suggestions.forEach {
                        suggestions.add(it.text)
                    }
                    suggestionAdapter.notifyDataSetChanged()
                }
                .addOnFailureListener {
                    it.printStackTrace()
                }
        }
    }

}
