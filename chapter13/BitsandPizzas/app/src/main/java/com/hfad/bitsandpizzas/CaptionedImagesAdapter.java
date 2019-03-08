package com.hfad.bitsandpizzas;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by davidg on 04/05/2017.
 */

class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    private String[] captions;
    private int[] imageIds;
    private Listener listener;

    interface Listener {
        /**
         * 点击事件
         *
         * @param position 位置
         */
        void onClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private ImageView mImageView;
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
            mImageView = (ImageView) itemView.findViewById(R.id.info_image);
            mTextView = (TextView) itemView.findViewById(R.id.info_text);
        }
    }

    public CaptionedImagesAdapter(String[] captions, int[] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CardView cardView = holder.mCardView;
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        holder.mImageView.setImageDrawable(drawable);
        holder.mImageView.setContentDescription(captions[position]);
        holder.mTextView.setText(captions[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    // TODO: 2019/3/8 获取在回收视图中的位置：索引
//                    listener.onClick(position);
                    listener.onClick(holder.getAdapterPosition());
                }
            }
        });
    }
}
