package teamB.market.domain.question.repository;

import org.springframework.stereotype.Repository;
import teamB.market.domain.question.Reply;

import java.util.*;

@Repository
public class MemoryReplyRepository implements ReplyRepository {

    private static Map<Long, Reply> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Reply save(Reply reply) {
        reply.setId(++sequence);
        store.put(reply.getId(), reply);
        return reply;
    }

    @Override
    public Reply findById(long id) {
        return store.get(id);
    }

    @Override
    public List<Reply> findByQuestionId(long questionId) {
        Iterator<Long> iterator = store.keySet().iterator();
        List<Reply> lists = new ArrayList<>();

        while(iterator.hasNext()){
            Long id = iterator.next();
            if(store.get(id).getQuestionId() == questionId){
                lists.add(store.get(id));
            }
        }

        return lists;
    }

    @Override
    public void delete(long id) {
        store.remove(id);
    }
}
