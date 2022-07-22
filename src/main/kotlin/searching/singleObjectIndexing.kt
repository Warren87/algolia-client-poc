package searching// Main.kt
import com.algolia.search.client.ClientSearch
import com.algolia.search.helper.deserialize
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.search.Query


suspend fun main(vararg args : String) {
    // Connect and authenticate with your Algolia app
    val client = ClientSearch(
        applicationID = ApplicationID(args[0]),
        apiKey = APIKey(args[1])
    )

    // Create a new index and add a record (using the `Record` class)
    val index = client.initIndex(indexName = IndexName("test_index"))
    val record = Record("first_record", ObjectID("1"))

    index.run {
        saveObject(Record.serializer(), record).wait()
    }

    // Search the index and print the results
    val response = index.run {
        search(Query("first_record"))
    }
    val results: List<Record> = response.hits.deserialize(Record.serializer())
    println(results[0])
}