package com.example.mytestapp.Activity.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mytestapp.Activity.Activity.DetailActivity
import com.example.mytestapp.Activity.Model.BigSummaryModel
import com.example.mytestapp.R
import kotlinx.android.synthetic.main.list_item_original_content_summary.view.*

class OriginalContentFeedPagedListAdapter(
    val context: Context,
    val itemClicked: (BigSummaryModel) -> Unit
) : PagedListAdapter<BigSummaryModel, RecyclerView.ViewHolder>(
    DIFF_COMPARATOR
) {


    private var lastPosition = -1


    companion object {
        private val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<BigSummaryModel>() {
            override fun areItemsTheSame(
                oldItem: BigSummaryModel,
                newItem: BigSummaryModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: BigSummaryModel,
                newItem: BigSummaryModel
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }

    }

    /**
     * Here is the key method to apply the animation
     */
    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.slide_from_bottom_to_top)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        (holder as OriginalContentFeedViewHolder).clearAnimation()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(context)
            .inflate(R.layout.list_item_original_content_summary, parent, false)
        return OriginalContentFeedViewHolder(
            view,
            itemClicked
        )


    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        val holder = holder as OriginalContentFeedViewHolder
        holder.binding(getItem(position))
        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);
    }


    class OriginalContentFeedViewHolder(
        val view: View,
        val itemClicked: (BigSummaryModel) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun clearAnimation() {
            view.clearAnimation()
        }

        fun binding(model: BigSummaryModel?) {
            view.summaryTitle.setText(model?.title)
            Glide.with(view.context)
                .load(model?.image_url)
                .placeholder(R.color.colorPink250)
                .error(R.color.colorGray200)
                .into(view.summaryImage)
//            view.category_banner.setImageResource(R.drawable.category_original_content_banner)
            view.category_banner?.setText("Original Content")

//            if (model?.tag_name != null) {
//
//                view.summaryTag.setText(model?.tag_name)
//            }
            view.curatorName.setText(model?.curator_name)
            view.summaryKiss.setText(model?.like.toString())
            val viewCount = model?.view
            if (viewCount!! < 1000) {
                view.number_of_views.setText(viewCount.toString() + "VIEWS")
            } else if (viewCount < 1000000) {
                view.number_of_views.setText((viewCount / 1000).toString() + "K " + "VIEWS")
            } else {
                view.number_of_views.setText((viewCount / 1000).toString() + "M " + "VIEWS")
            }
            itemView.setOnClickListener {
                itemClicked(model)
                val intent = Intent(view.context, DetailActivity::class.java)
                view.context.startActivity(intent)

            }

        }
    }
}

