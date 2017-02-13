package com.chandy.circletap.circletap.MVP.Model;

import android.content.Context;

/**
 * Created by Chandy on 2017/2/13.
 */

public interface IRecord {
    int ReadLast(Context context);
    int ReadBest(Context context);
    void WriteLast(Context context,int last);
    void WriteBest(Context context,int best);
}
