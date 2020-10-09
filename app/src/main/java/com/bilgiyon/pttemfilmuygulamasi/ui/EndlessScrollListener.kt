package com.bilgiyon.pttemfilmuygulamasi.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener(
    layoutManager: LinearLayoutManager,
    listener: ScrollToBottomListener,
    pageNumber: Int
) : RecyclerView.OnScrollListener() {
    var previousTotal = 0
    var totalItemCount = 0
    var visibleItemCount = 0
    var loading = true
    var firstVisibleItem = 0
    var visibleThreshold = 3
    var nextPageNumber = 0

    val layoutManager: LinearLayoutManager
    val scrollListener: ScrollToBottomListener

    init {
        this.layoutManager = layoutManager
        this.scrollListener = listener
        this.nextPageNumber = pageNumber
    }

    fun onRefresh() {
        previousTotal = 0;
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
            <= (firstVisibleItem + visibleThreshold)
        ) {
            nextPageNumber++
            this.scrollListener.onScrollToBottom(nextPageNumber);
            loading = true;
        }
    }
}