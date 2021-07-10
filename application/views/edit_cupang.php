<div class="col-lg-6">

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Edit List Ikan Cupang</h1>
<!-- DataTales Example -->
    <div class="card shadow mb-4 mt-2">
        <div class="card-header py-3">
        </div>
        <div class="card-body">
        <form method="POST" enctype="multipart/form-data" action="<?= base_url('dashboard/proses_edit_cupang/'.$datamenu[0]['id_cupang']); ?>">
        <div class="form-group">
                <label for="exampleInputEmail1">Nama Cupang</label>
                <input type="text" class="form-control" name="nama_cupang" required>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Harga Cupang</label>
                <input type="text" class="form-control" name="harga_cupang" required>
            </div>
            <label for="gambar">Gambar</label>
                    <input type="file" class="form-control" name="file" id="file">
                    </label>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-success">Simpan</button>
        </form>
      </div>
        </div>
    </div>

</div>