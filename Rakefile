FEATURE_EP='com.microsoft.excel_online.causelizer.feature_engineering.FeatureSetReader'
FEATURE_DIR='_data'
TRGT_JAR='target/causalizer-1.0.jar'
MVN='mvn'

task :default => :run_feature_eng

task :package do
  system("#{MVN} package")
end

task :run_feature_eng => :package do
  system("spark-submit --master local \
    --driver-memory 2g \
    --executor-memory 2g \
    --class #{FEATURE_EP} \
    #{TRGT_JAR} \
    --inputdir #{FEATURE_DIR}")
end
