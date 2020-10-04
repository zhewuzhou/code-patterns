package zhewu.me.code.patterns.before.classwithfactory.mappers;

import zhewu.me.code.patterns.before.classwithfactory.descriptors.AttributeDescriptor;
import zhewu.me.code.patterns.before.classwithfactory.descriptors.DefaultDescriptor;
import zhewu.me.code.patterns.before.classwithfactory.descriptors.ReferenceDescriptor;
import zhewu.me.code.patterns.before.classwithfactory.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DescriptorMapper {
    protected List<AttributeDescriptor> createAttributeDescriptors() {
        ArrayList<AttributeDescriptor> result = new ArrayList<>();
        result.add(new DefaultDescriptor("remoteId", getClass(), Integer.class));
        result.add(new DefaultDescriptor("createdDate", getClass(), LocalDateTime.class));
        result.add(new DefaultDescriptor("lastChangedDate", getClass(), LocalDateTime.class));
        result.add(new ReferenceDescriptor("createdBy", getClass(), User.class));
        result.add(new ReferenceDescriptor("lastChangedBy", getClass(), User.class));
        result.add(new DefaultDescriptor("optimisticLockVersion", getClass(), Integer.class));
        return result;
    }
}
