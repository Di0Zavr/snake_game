import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun authenticate(email: String, password: String): User?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("UPDATE users SET high_score = :highScore WHERE id = :userId")
    suspend fun updateHighScore(userId: Int, highScore: Int)

    @Query("SELECT * FROM users ORDER BY high_score DESC LIMIT 10")
    fun getTopUsers(): Flow<List<User>>

}
