/*
   Copyright 2010-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
   This file is licensed under the Apache License, Version 2.0 (the "License").
   You may not use this file except in compliance with the License. A copy of
   the License is located at
    http://aws.amazon.com/apache2.0/
   This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
   CONDITIONS OF ANY KIND, either express or implied. See the License for the
   specific language governing permissions and limitations under the License.
*/
package zhihaow.example.s3;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import java.util.List;
import java.io.InputStream;


/**
 * Test Stream copy S3 files.
 *
 * This code expects that you have AWS credentials set up per:
 * http://docs.aws.amazon.com/java-sdk/latest/developer-guide/setup-credentials.html
 */
public class StreamCopy 
{
    public static void main(String[] args)
    {
        final AmazonS3 s3 = new AmazonS3Client();
	final String from_bucket = "data-platform-prod";
	final String to_bucket = from_bucket;
	final String from_object_key = "e_input/d_unified_inv_level_by_owner/region_id=1/snapshot_date=2017-04-05/d_unified_inv_level_by_owner.dat";
	final String to_object_key = "zhihaow/tmp/d_unified_inv_level_by_owner.dat";
        S3Object s3Object = s3.getObject(from_bucket, from_object_key);
        InputStream objectData = s3Object.getObjectContent();
        s3.putObject(to_bucket, to_object_key, objectData, s3.getObjectMetadata(from_bucket, from_object_key));
    }
}
