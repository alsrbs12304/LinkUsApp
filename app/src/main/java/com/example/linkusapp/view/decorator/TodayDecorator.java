package com.example.linkusapp.view.decorator;

import android.content.Context;
import android.graphics.Typeface;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.example.linkusapp.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Date;

public class TodayDecorator implements DayViewDecorator {

    private CalendarDay date;
    Context context;

    public TodayDecorator(Context context) {
        date = CalendarDay.today();
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new StyleSpan(Typeface.NORMAL));
        view.addSpan(new DotSpan(5, context.getResources().getColor(R.color.red400)));
        view.addSpan(new RelativeSizeSpan(1.4f));
//        view.addSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.black)));

    }

    public void setDate(Date date) {
        this.date = CalendarDay.from(date);

    }
}
