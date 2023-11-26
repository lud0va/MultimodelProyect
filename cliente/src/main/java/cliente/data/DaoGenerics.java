package cliente.data;

import cliente.domain.errores.ErrorClient;
import com.google.gson.Gson;

import errores.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import okhttp3.MediaType;

import retrofit2.HttpException;
import retrofit2.Response;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class DaoGenerics {


    private final Gson gson;

    @Inject
    protected DaoGenerics(Gson gson) {
        this.gson = gson;
    }



    public <T> Single<Either<ErrorClient, T>> safeSingleApicall(Single<T> call) {
        return call.map(t ->
                        Either.right(t).mapLeft(o -> (ErrorClient) o)
                )
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    Either<ErrorClient, T> error = Either.left(new ErrorClient("Error de comunicacion"));

                    if (throwable instanceof HttpException) {
                        int code = ((HttpException) throwable).code();

                        if (((HttpException) throwable).response().errorBody() != null) {
                            if (Objects.equals(((HttpException) throwable).response().errorBody().contentType(), MediaType.get("application/json"))) {
                                ApiError api = gson.fromJson(((HttpException) throwable).response().errorBody().charStream(), ApiError.class);
                                error = Either.left(new ErrorClient(code + api.getMessage()));


                                //error = Either.right(T);
                            } else {
                                error = Either.left(new ErrorClient(((HttpException) throwable).response().message()));
                            }
                        }
                    }
                    return error;
                });


    }

    public Single<Either<ErrorClient, String>> safeSingleVoidApicall(Single<Response<Void>> call) {
        return call.map(response -> {
                    Either<ErrorClient, String> retorno = Either.right("OK");
                    if (!response.isSuccessful()) {
                        retorno = Either.left(new ErrorClient(response.message()));
                    }
                    return retorno;
                })
                .subscribeOn(Schedulers.io());
    }
}
