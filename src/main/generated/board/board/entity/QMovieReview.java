package board.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovieReview is a Querydsl query type for MovieReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovieReview extends EntityPathBase<MovieReview> {

    private static final long serialVersionUID = -379365619L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMovieReview movieReview = new QMovieReview("movieReview");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final QMovieMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final QMovie movie;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> rno = createNumber("rno", Long.class);

    public final StringPath text = createString("text");

    public QMovieReview(String variable) {
        this(MovieReview.class, forVariable(variable), INITS);
    }

    public QMovieReview(Path<? extends MovieReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMovieReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMovieReview(PathMetadata metadata, PathInits inits) {
        this(MovieReview.class, metadata, inits);
    }

    public QMovieReview(Class<? extends MovieReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMovieMember(forProperty("member")) : null;
        this.movie = inits.isInitialized("movie") ? new QMovie(forProperty("movie")) : null;
    }

}

