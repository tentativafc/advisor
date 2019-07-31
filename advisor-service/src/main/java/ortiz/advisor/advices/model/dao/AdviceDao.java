package ortiz.advisor.advices.model.dao;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Repository;
import ortiz.advisor.advices.model.entity.Advice;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AdviceDao {
    private static final String KEY = "advices";

    @Resource(name = "redisTemplate")
    private ListOperations<String, Advice> opsForList;

    public void addAdvice(Advice advice) {
        opsForList.leftPush(KEY, advice);
    }

    public long count() {
        return opsForList.size(KEY);
    }

    public Advice getAdviceAtIndex(Integer index) {
        return opsForList.index(KEY, index);
    }

    public List<Advice> getAll() {
        return opsForList.range(KEY, 0, -1);
    }

    public void removeAll() {
        while(opsForList.leftPop(KEY) != null) {
        }
    }

    public void removeAdvice(Advice advice) {
        opsForList.remove(KEY, 1, advice);
    }
}
