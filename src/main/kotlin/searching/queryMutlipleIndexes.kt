package searching// Main.kt
import com.algolia.search.client.ClientSearch
import com.algolia.search.helper.deserialize
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.multipleindex.IndexQuery
import com.algolia.search.model.search.Query

const val FIRST_INDEX_NAME = "test_index"

const val SECOND_INDEX_NAME = "test_another_index"

suspend fun main(vararg args : String) {
    // Connect and authenticate with your Algolia app
    val client = ClientSearch(
        applicationID = ApplicationID(args[0]),
        apiKey = APIKey(args[1])
    )
    
    val firstIndex = client.initIndex(indexName = IndexName(FIRST_INDEX_NAME))
    
    firstIndex.run {
        saveObject(Record.serializer(), Record("first_idx_record", ObjectID("1"))).wait()
    }


    val secondIndex = client.initIndex(indexName = IndexName(SECOND_INDEX_NAME))
    secondIndex.run {
        saveObject(Record.serializer(), Record("second_idx_record", ObjectID("1"))).wait()
    }
    
    
    
    val queries = listOf(
        IndexQuery(
            IndexName(FIRST_INDEX_NAME),
            Query(query = "first_idx_record", hitsPerPage = 3)
        ),
        IndexQuery(
            IndexName(SECOND_INDEX_NAME),
            Query(query = "second_idx_record", hitsPerPage = 3)
        ),
    )
    
    val searches =  client.multipleQueries(queries)

    val results = searches.results.flatMap { it.hits.deserialize(Record.serializer()) }
    
    println(results) // should contains two different records
}