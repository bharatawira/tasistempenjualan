<div class="col-lg-6">

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">Edit Karyawan</h1>
<!-- DataTales Example -->
    <div class="card shadow mb-4 mt-2">
        <div class="card-header py-3">
        </div>
        <div class="card-body">
        <form method="POST" enctype="multipart/form-data" action="<?= base_url('dashboard/proses_edit_user/'.$datamenu[0]['id']); ?>">
            <div class="form-group">
                <label for="exampleInputEmail1">Name</label>
                <input type="text" class="form-control" name="name" required>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Username</label>
                <input type="text" class="form-control" name="username" required>
            </div>
            <div class="form-group">
                <label for="exampleInputEmail1">Password</label>
                <input type="text" class="form-control" name="password" required>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Role</label>
                    <select class="custom-select" id="inputGroupSelect01" name="role" required>
                    <option selected>pilih...</option>
                    <option value="Admin">admin</option>
                    <option value="User">user</option>
                </select>
                </div>
                
            </div>
            </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-success">Simpan</button>
        </form>
      </div>
        </form>
        </div>
    </div>

</div>