package trickle.jdk8.lang;

import java.util.logging.Logger;

/**
 * <p>
 * Title: InterfaceConcreteMethods
 * <p>
 * Description: Concrete method of interface demos.
 *
 * @author duanjunfeng
 * @since 2017
 */
public class InterfaceConcreteMethods {

    private static final Logger GLOBAL = Logger.getGlobal();

    // interface with default method and static method
    private static interface Lifecycle {

        /**
         * key.1. with default method
         * <p>
         * 使用接口的默认方法可以省去定义一个公共抽象类
         */
        public default void log(String message) {
            GLOBAL.info(message);
        }

        /**
         * key.2. with static method
         * <p>
         * 接口的静态方法可以省去定义一个公共工具类
         */
        public static String fromState(LifecycleState state) {
            return state.name();
        }

        public void start();

        public void stop();

        public LifecycleState getState();
    }

    private static enum LifecycleState {
        NEW,
        STARTED,
        STOPPED;
    }

    private static class Container implements Lifecycle {

        private LifecycleState state = LifecycleState.NEW;

        @Override
        public void start() {
            log("start");
            state = LifecycleState.STARTED;
            log(Lifecycle.fromState(state));
        }

        @Override
        public void stop() {
            log("stop");
            state = LifecycleState.STOPPED;
            log(Lifecycle.fromState(state));
        }

        @Override
        public LifecycleState getState() {
            return this.state;
        }

    }

    public static void main(String[] args) {
        Container container = new Container();
        container.start();
        container.stop();
        container = null;
    }

}
