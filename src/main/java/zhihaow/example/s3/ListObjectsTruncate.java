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
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.util.List;
import java.io.InputStream;


/**
 * Test How many will listObjects start to truncate. 
 * By executing "gradle run | grep e_output | wc -l", it turns out the turncate threshold is 1000.
 */
public class ListObjectsTruncate 
{
    public static void main(String[] args)
    {
        final AmazonS3 s3 = new AmazonS3Client();
	final String bucket = "data-platform-prod";
	final String key = "e_output/inventory_aged_data_source_v2/region_id=1/";
        ObjectListing listing = s3.listObjects(bucket, key);
        System.out.println("Truncate:" + listing.isTruncated());
        for(S3ObjectSummary summary: listing.getObjectSummaries())
            System.out.println(summary.getKey());
    }
}
