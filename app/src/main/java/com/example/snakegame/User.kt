import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")  // Явно указываем имя таблицы
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "email")  // Явно указываем имя столбца
    val email: String,

    @ColumnInfo(name = "username")
    val name: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "high_score")
    var highScore: Int = 0,

)
