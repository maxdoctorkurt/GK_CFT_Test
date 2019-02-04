package com.example.maxdo.gk_cft_test.editing.modifiersPanel

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.maxdo.gk_cft_test.R
import com.example.maxdo.gk_cft_test.core.mvi.CustomRxBindings
import com.hannesdorfmann.mosby3.mvi.MviFragment
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import kotlinx.android.synthetic.main.image_editing_panel.*
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File
import java.util.concurrent.TimeUnit


class EditingImageFragment : MviFragment<EditingImageView, EditingImagePresenter>(),
    EditingImageView {

    private var pickImageIntent = ReplaySubject.create<File>()

    override fun createPresenter(): EditingImagePresenter {
        return EditingImagePresenter()
    }

    override fun getImageIntent(): Observable<File> {
        return pickImageIntent
    }

    override fun getRotateImageIntent(): Observable<Boolean> {
        return CustomRxBindings.viewClicks(btn_rotate).map { true }
    }

    override fun getInvertImageIntent(): Observable<Boolean> {
        return CustomRxBindings.viewClicks(btn_invert).map { true }
    }

    override fun getMirrorImageIntent(): Observable<Boolean> {
        return CustomRxBindings.viewClicks(btn_mirror).map { true }
    }

    override fun getGreyScaleImageIntent(): Observable<Boolean> {
        return CustomRxBindings.viewClicks(btn_greyscale).map { true }
    }

    override fun render(viewState: EditingImageViewState) {

        fl_progress.visibility = if (viewState.isProgress) View.VISIBLE else View.GONE
        tv_tapToAddPlaceholder.visibility = if (viewState.previewImage != null) View.GONE else View.VISIBLE

        if (viewState.previewImage != null) {
            Picasso
                .get()
                .load(viewState.previewImage!!)
                .centerCrop()
                .fit()
                .error(R.drawable.broken_image)
                .into(iv_imagePreview)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv_imagePreview.setOnClickListener {
            showImageSelectionDialog()
        }

    }

    private fun showImageSelectionDialog() {
        Dexter.withActivity(activity)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    EasyImage.openChooserWithDocuments(
                        this@EditingImageFragment,
                        getString(R.string.choose_image_source),
                        0
                    )
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    println("*** onPermissionRationaleShouldBeShown")
//                        Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show()
                }

            }).check()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_editing_panel, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        EasyImage.handleActivityResult(requestCode, resultCode, data, activity, object : DefaultCallback() {

            override fun onCanceled(source: EasyImage.ImageSource?, type: Int) {
                if (source === EasyImage.ImageSource.CAMERA) {
                    val photoFile = EasyImage.lastlyTakenButCanceledPhoto(activity)
                    photoFile?.delete()
                }
            }

            override fun onImagePicked(imageFile: File?, source: EasyImage.ImageSource?, type: Int) {
                if (imageFile != null) {
                    if(pickImageIntent.hasComplete())  pickImageIntent = ReplaySubject.create()
                    pickImageIntent.onNext(imageFile)
                }
            }

            override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) {
                Toast.makeText(context, R.string.image_picking_error, Toast.LENGTH_LONG).show()
            }

        })
    }

}



