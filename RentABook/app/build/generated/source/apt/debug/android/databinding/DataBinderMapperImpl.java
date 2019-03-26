package android.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.example.cmput301w19t18.rent_a_book.DataBinderMapperImpl());
  }
}
