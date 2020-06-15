package github.coderbuck.codehub.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

/**
 * 拓展类
 */

/* Activity */
val FragmentActivity.contentView: View
    get() = (findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)

fun <T : ViewModel> FragmentActivity.provideViewModel(clazz: Class<T>) =
    ViewModelProvider(this)[clazz]


/* Fragment */
fun <T : ViewModel> Fragment.provideViewModel(clazz: Class<T>) = ViewModelProvider(this)[clazz]
fun <T : ViewModel> Fragment.provideSharedViewModel(clazz: Class<T>) =
    ViewModelProvider(requireActivity())[clazz]


/* RecyclerView */
fun <VH : RecyclerView.ViewHolder> RecyclerView.Adapter<VH>.inflate(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
): View {
    return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
}

/* LiveData */
val <T>MutableLiveData<T>.valueNN: T
    get() = value!!

