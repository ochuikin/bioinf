package com.babichev.oleg.bioinformatic.core;

/**
 * Created by olegchuikin on 14/02/16.
 */
public class DNA extends BaseGenome {

    private String reverseComponent;

    public DNA(String symbols) {
        super(symbols);
        this.reverseComponent = makeReverseComponent(symbols);
    }

    public RNA makeRNA() {
        StringBuilder sb = new StringBuilder();
        for (char c : symbols.toCharArray()) {
            switch (c) {
                case 'T':
                    sb.append('U');
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return new RNA(sb.toString());
    }


    public String getReverseComponent() {
        return reverseComponent;
    }



}
