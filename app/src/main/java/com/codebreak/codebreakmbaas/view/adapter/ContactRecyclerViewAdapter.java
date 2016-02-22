package com.codebreak.codebreakmbaas.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codebreak.codebreakmbaas.R;
import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by PedroFelipe on 18/02/2016.
 */
public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.ContactViewHolder> {

    private View mView;
    private Context mContext;
    private List<ParseObject> mContacts;

    public ContactRecyclerViewAdapter(Context context, List<ParseObject> contacts) {
        this.mContext = context;
        this.mContacts = contacts;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(this.mView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.setIsRecyclable(false);

        if (mContacts.get(position).getParseFile("imageFile") != null) {
            Picasso.with(this.mContext).load(Uri.parse(mContacts.get(position).getParseFile("imageFile").getUrl())).into(holder.mCircleImageViewContact);
        } else {
            Picasso.with(this.mContext).load(R.drawable.backendless).into(holder.mCircleImageViewContact);
        }

        holder.mTextViewContactName.setText(this.mContacts.get(position).getString("name"));
        holder.mTextViewContactPhoneNumber.setText(this.mContacts.get(position).getString("phoneNumber"));

    }

    @Override
    public int getItemCount() {
        return (null != this.mContacts && this.mContacts.size() > 0 ? this.mContacts.size() : 0);
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.circle_image_view_contact)
        CircleImageView mCircleImageViewContact;

        @Bind(R.id.text_view_contact_name)
        TextView mTextViewContactName;

        @Bind(R.id.text_view_contact_phone_number)
        TextView mTextViewContactPhoneNumber;

        public ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(ContactViewHolder.this, itemView);
        }
    }

}
