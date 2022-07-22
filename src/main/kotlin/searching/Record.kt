package searching

import com.algolia.search.model.ObjectID
import com.algolia.search.model.indexing.Indexable
import kotlinx.serialization.Serializable

// A simple record for your index
@Serializable
internal data class Record(
    val name: String,
    override val objectID: ObjectID
) : Indexable
