package org.qasimovey.utils;


public class ColumnUtils {
    public static final String ID="ID";
    public static final String MESSAGE="MESSAGE";
    public static final String STATUS="STATUS";
    public static final String CREATED_DATE="CREATED_DATE";

    private ColumnUtils() throws IllegalAccessException{
        throw new IllegalAccessException();
    }

    public enum STATE{
        SENT(1),
        NOT_SENT(0);

        public final int state;

        private STATE(int state) {
            this.state = state;
        }
    }
}
