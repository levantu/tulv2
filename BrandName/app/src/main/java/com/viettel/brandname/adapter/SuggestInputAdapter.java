package com.viettel.brandname.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.viettel.brandname.R;
import com.viettel.brandname.model.SuggestInputModel;

import java.util.ArrayList;
import java.util.Locale;

public class SuggestInputAdapter extends ArrayAdapter<SuggestInputModel> {
    private ArrayList<SuggestInputModel> items;
    private ArrayList<SuggestInputModel> itemsAll;
    private ArrayList<SuggestInputModel> suggestions;
    private LayoutInflater inflater;
    private Context mContext;
    private String keyword;

    public SuggestInputAdapter(Context context, ArrayList<SuggestInputModel> items) {
        super(context, R.layout.item_suggest, items);
        this.items = items;
        this.itemsAll = (ArrayList<SuggestInputModel>) items.clone();
        this.suggestions = new ArrayList<SuggestInputModel>();
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void updateItem(ArrayList<SuggestInputModel> items) {
        this.items = items;
        this.itemsAll = (ArrayList<SuggestInputModel>) items.clone();
        this.suggestions.clear();
        this.notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_suggest, null, false);
            viewHolder = new ViewHolder();
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewHolder.txtPhoneNumber = (TextView) convertView.findViewById(R.id.txtPhoneNumber);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SuggestInputModel tmpSuggestInput = items.get(position);
        final ForegroundColorSpan myColor = new ForegroundColorSpan(Color.parseColor("#0097c2"));
        viewHolder.txtDescription.setText(mContext.getResources().getString(R.string.send_sms_group));
        SpannableString myString = new SpannableString(tmpSuggestInput.getName());
        int index = tmpSuggestInput.getName().toLowerCase(Locale.getDefault()).indexOf(keyword);

        if (index >= 0) {
            myString.setSpan(myColor, index, index + keyword.length(), 0);
        }
        viewHolder.txtName.setText(myString);
        viewHolder.txtPhoneNumber.setText("(" + tmpSuggestInput.getDescription() + ")");
        return convertView;
    }

    static class ViewHolder {
        private TextView txtName, txtPhoneNumber, txtDescription;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            SuggestInputModel str = ((SuggestInputModel) (resultValue));
            return str.getName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null && !constraint.toString().trim().equals("")) {
                suggestions.clear();
                keyword = constraint.toString().trim().toLowerCase(Locale.getDefault());
                for (SuggestInputModel item : itemsAll) {
                    String str1 = null;
                    if (item.getName() != null) {
                        str1 = item.getName().toLowerCase(Locale.getDefault());
                    }
                    String str2 = null;
                    if (item.getDescription() != null) {
                        str2 = item.getDescription().toLowerCase(Locale.getDefault());
                    }
                    String str3 = constraint.toString().trim().toLowerCase(Locale.getDefault());
                    if ((str1 != null && str1.contains(str3)) || (str2 != null && str2.contains(str3))) {
                        suggestions.add(item);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            @SuppressWarnings("unchecked")
            ArrayList<SuggestInputModel> filteredList = (ArrayList<SuggestInputModel>) results.values;
            if (results != null && results.count > 0) {
                items.clear();
                for (SuggestInputModel c : filteredList) {
                    items.add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

    @Override
    public int getCount() {
        return items.size();
    }
}