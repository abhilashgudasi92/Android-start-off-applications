package com.example.android.miwok;

/**
  * Created by Arun on 5/19/2017.
  * {@link Word} represents a vocabulary word that the user wants to learn.
  * It contains a default translation and a Miwok translation for that word.
 */

public class Word {
    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    /**Image ID */
    private int mImageResourceId = mImagePresence;

    /** Audio resource ID for the word */
    private int mAudioResourceId;

    /**Image detect */
    private static final int mImagePresence = -1;

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    public  Word(String defaultTranslation,String miwokTranslation,int audioResourceId){
            mDefaultTranslation = defaultTranslation;
            mMiwokTranslation = miwokTranslation;
            mAudioResourceId = audioResourceId;
    }

    public  Word(String defaultTranslation,String miwokTranslation,int imageResourceId,int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }


    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    /**
     * Get the Image resource ID of the word.
     */
    public int getImageResourceId(){
        return  mImageResourceId;
    }

    /**
     * Get the know whether valid image is present or not in the word.
     */
    public boolean getImagePresence(){
        return  mImageResourceId != mImagePresence ;
    }

    /**
     * Return the audio resource ID of the word.
     */
     public int getAudioResourceId() {
            return mAudioResourceId;
     }
}
