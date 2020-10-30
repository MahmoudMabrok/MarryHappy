package mahmoudmabrok.happymarry.viewmodels

import androidx.lifecycle.ViewModel
import mahmoudmabrok.happymarry.dataLayer.models.NonVideo
import mahmoudmabrok.happymarry.dataLayer.models.VideoListItem

class VideoListViewmodel : ViewModel() {
    var lsitintem: VideoListItem? = null
    var nonVideoItem: NonVideo? = null
}