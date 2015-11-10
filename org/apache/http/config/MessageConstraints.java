package org.apache.http.config;

import org.apache.http.util.Args;

public class MessageConstraints implements Cloneable {
    public static final MessageConstraints DEFAULT;
    private final int maxHeaderCount;
    private final int maxLineLength;

    public static class Builder {
        private int maxHeaderCount;
        private int maxLineLength;

        Builder() {
            this.maxLineLength = -1;
            this.maxHeaderCount = -1;
        }

        public Builder setMaxLineLength(int i) {
            this.maxLineLength = i;
            return this;
        }

        public Builder setMaxHeaderCount(int i) {
            this.maxHeaderCount = i;
            return this;
        }

        public MessageConstraints build() {
            return new MessageConstraints(this.maxLineLength, this.maxHeaderCount);
        }
    }

    static {
        DEFAULT = new Builder().build();
    }

    MessageConstraints(int i, int i2) {
        this.maxLineLength = i;
        this.maxHeaderCount = i2;
    }

    public int getMaxLineLength() {
        return this.maxLineLength;
    }

    public int getMaxHeaderCount() {
        return this.maxHeaderCount;
    }

    protected MessageConstraints clone() throws CloneNotSupportedException {
        return (MessageConstraints) super.clone();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[maxLineLength=").append(this.maxLineLength).append(", maxHeaderCount=").append(this.maxHeaderCount).append("]");
        return stringBuilder.toString();
    }

    public static MessageConstraints lineLen(int i) {
        return new MessageConstraints(Args.notNegative(i, "Max line length"), -1);
    }

    public static Builder custom() {
        return new Builder();
    }

    public static Builder copy(MessageConstraints messageConstraints) {
        Args.notNull(messageConstraints, "Message constraints");
        return new Builder().setMaxHeaderCount(messageConstraints.getMaxHeaderCount()).setMaxLineLength(messageConstraints.getMaxLineLength());
    }
}
