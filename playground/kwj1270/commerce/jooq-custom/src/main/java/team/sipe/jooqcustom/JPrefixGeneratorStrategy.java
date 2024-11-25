package team.sipe.jooqcustom;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class JPrefixGeneratorStrategy extends DefaultGeneratorStrategy {

    @Override
    public String getJavaClassName(final Definition definition, final Mode mode) {
        if(mode == Mode.DEFAULT) {
            return super.getJavaClassName(definition, mode) + "JooqEntity";
        }
        return super.getJavaClassName(definition, mode);
    }
}
