package br.senai.sp.jandira.kalos_app.service

import br.senai.sp.jandira.kalos_app.model.AcademiaResponse
import br.senai.sp.jandira.kalos_app.model.AlunoResponse
import br.senai.sp.jandira.kalos_app.model.BaseResponse
import br.senai.sp.jandira.kalos_app.model.BaseResponse2
import br.senai.sp.jandira.kalos_app.model.BaseResponseAcademia
import br.senai.sp.jandira.kalos_app.model.BaseResponseStatus
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AlunoService {
    @GET("kalos/aluno/id/{id}")
    suspend fun getAlunoByID(@Path("id") id: String): Response<BaseResponse<AlunoResponse>>

    @GET("kalos/aluno/email/{email}")
    suspend fun getAlunoByEmail(@Path("email") email: String): Response<BaseResponseStatus<AlunoResponse>>

    @GET("kalos/aluno")
    suspend fun getAlunos(): Response<BaseResponse<AlunoResponse>>

    @GET("kalos/alunoAcademia/idAluno/{id}")
    suspend fun getAlunoAcademias(@Path("id") id:String): Response<BaseResponseAcademia<AcademiaResponse>>

    @Headers("Content-Type: application/json")
    @POST("kalos/aluno/autenticar")
    suspend fun autenticarAluno(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("kalos/aluno/esqueci_senha")
    suspend fun esqueciSenha(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("kalos/aluno/validar_token")
    suspend fun enviarCodigo(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("kalos/aluno")
    suspend fun cadastrarAluno(@Body body: JsonObject): Response<BaseResponseStatus<AlunoResponse>>

    @Headers("Content-Type: application/json")
    @PUT("kalos/aluno/id/{id}")
    suspend fun AtualizarAluno(@Body body: JsonObject, @Path("id") id:String): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @PUT("kalos/aluno/redefinir_senha")
    suspend fun atualizarSenhaAluno(@Body body: JsonObject): Response<JsonObject>


}

